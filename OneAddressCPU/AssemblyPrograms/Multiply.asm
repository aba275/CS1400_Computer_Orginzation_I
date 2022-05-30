.text
# Begin the multiplication loop.
.label startLoop

# When the program is assembled the multiplier and multiplicand are initialized 
# in memory.  The counter is initialized to 0 and incremented by 1 each time through
# the loop.  When the counter == multiplier, the value of multiplier-counter = 0, and the 
#  beqz instruction branches to the end of the loop.
clac
add multiplier
sub counter
beqz endLoop  # Go to end when done

# calculate product.  The product is initially zero, but each time through the loop the product
# is augmented by the value of the multiplicand.  The product is then stored back to memory 
# for use on the next pass.
clac
add product
add multiplicand
stor product

# The counter is incremented and the program branches back to the beginning of the loop for
# the next pass.
clac
add counter
addi 1
stor counter
clac
beqz startLoop

# When counter == multiplier, the program has completed.  The answer is at the memory 
# location of the label product.
.label endLoop
clac
beqz endLoop
# result is in ans (data address 0)

.data
.label multiplicand
     .number 5
.label multiplier 
    .number 4
.label counter 
    .number 0 
.label product  
    .number 0
