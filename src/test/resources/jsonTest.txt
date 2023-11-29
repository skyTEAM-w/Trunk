from src.CWRU.config import Config
from src.CWRU.CWRUdata import CWRUdata
from src.CWRU.utils import create_dataset, create_model
from torch.utils.data import DataLoader
from utils import check_accuracy
import torch
from tensorboardX import SummaryWriter
import copy
import time
import pandas as pd
from torchsummary import summary

# %%
opt = Config()
# %%
train_dataset = create_dataset(opt.train_dir, 'train')
train_dataloader = DataLoader(train_dataset, batch_size=opt.batch_size, shuffle=True)
# %%
val_dataset = create_dataset(opt.val_dir, 'val')
val_dataloader = DataLoader(val_dataset, batch_size=opt.batch_size, shuffle=True)
# %%
print('#training num = %d' % len(train_dataset))
print('#val num = %d' % len(val_dataset))
# %%
model = create_model(opt.model, opt.model_param)
writer = SummaryWriter()
# %%
print(model)
# %% md
## 模型训练
# %%
total_steps = 0
optimizer = torch.optim.Adam(filter(lambda p: p.requires_grad, model.parameters()), lr=opt.lr)
scheduler = torch.optim.lr_scheduler.StepLR(optimizer, opt.lr_decay_iters, opt.lr_decay)
loss_fn = torch.nn.CrossEntropyLoss()
# %%
use_cuda = torch.cuda.is_available()

if use_cuda:
    print('CUDA is available')

device = torch.device(opt.device if use_cuda else "cpu")
model.to(device)

best_model_wts = copy.deepcopy(model.state_dict())
best_acc = 0.0
# %%
for epoch in range(opt.epochs):
    t0 = time.time()
    print('Starting epoch %d / %d' % (epoch + 1, opt.epochs))
    scheduler.step()
    model.train()

    for t, (x, y) in enumerate(train_dataloader):
        # print(x)
        # print(x.size())

        x.resize(x.size()[0], x.size()[1])
        x = x.unsqueeze(1)
        # print(x)
        # print(x.size())

        x, y = x.float(), y.long()
        x, y = x.to(device), y.to(device)
        score = model(x)
        loss = loss_fn(score, y)
        writer.add_scalar('loss', loss.item())

        if (t + 1) % opt.print_every:
            print('t = %d, loss = %.4f' % (t + 1, loss.item()))
            pass

        optimizer.zero_grad()
        loss.backward()
        optimizer.step()
        pass

    train_acc, _ = check_accuracy(model, train_dataloader, device)
    val_acc, _ = check_accuracy(model, val_dataloader, device)

    # 添加acc 与 权重到tensorboard

    writer.add_scalar('train_acc', train_acc, epoch)
    writer.add_scalar('val_acc', val_acc, epoch)

    for name, param in model.named_parameters():
        writer.add_histogram(name, param.clone().cpu().data.numpy(), epoch)
    # save the best model
    if val_acc > best_acc:
        best_acc = val_acc
        best_model_wts = copy.deepcopy(model.state_dict())

    t1 = time.time()

    print(t1 - t0)

    pass

# 输出结果
print('kernel num1: {}'.format(opt.model_param['kernel_num1']))
print('kernel num2: {}'.format(opt.model_param['kernel_num2']))
print('Best val Acc: {:4f}'.format(best_acc))

# %%
# load best model weights
model.load_state_dict(best_model_wts)
val_acc, confuse_matrix = check_accuracy(model, val_dataloader, device, error_analysis=True)

# %%
# write the confuse_matrix to Excel
data_pd = pd.DataFrame(confuse_matrix)
writer = pd.ExcelWriter('results\\confuse_matrix_rate.xlsx')
data_pd.to_excel(writer)
writer.save()
writer.close()

# %%
# save model in results dir
model_save_path = 'results\\' + time.strftime('%Y%m%d%H%M_') + str(int(100 * best_acc)) + '.pth'
torch.save(model.state_dict(), model_save_path)
print('best model is saved in: ', model_save_path)
