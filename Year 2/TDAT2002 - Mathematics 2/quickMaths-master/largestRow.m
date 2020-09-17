function sum = largestRow(A)

    sum = -99999999;
    
    [n, m] = size(A);
    
    for i = 1:n
        
        rowsum = 0;
        
        for j = 1:m
            
            rowsum = rowsum + A(i, j);
            
        end
        
        if sum < rowsum
            
            sum = rowsum;
            
        end
        
    end
    
    sum = abs(sum);

end

