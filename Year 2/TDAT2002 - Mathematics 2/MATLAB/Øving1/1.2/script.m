%(a)x3 =2x+2(b)ex+x=7(c)ex +sinx=4.
%a
f = @(x)(2*x+2)^(1/3);
r1 = fpi(f,1,10);
%b
g = @(x)log(7-x);
r2 = fpi(g,1,10);
%c
h = @(x)log(4-sin(x));
r3 = fpi(h,1,10);




