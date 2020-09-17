x = -1: -1: -14;
x = 10.^x;
res = ones(14,3);
res(1:14 ,1) = x;
res(1:14,2)  = cp_1a(x); 
res(1:14,3)  = cp_1b(x); 
disp(res);
