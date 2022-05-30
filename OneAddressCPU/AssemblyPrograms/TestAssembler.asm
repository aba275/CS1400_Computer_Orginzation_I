.text
# Begin the multiplication loop.
.label startLoop

# When the program is assembled the arrayBase address points to the 0'th
# element in the array (the first element), and the arraySize is set to the 
# number of elements in the array.  The summation is initialized 
# 0.  The counter is initialized to 0 and incremented by 1 each time through
# the loop.  When the counter == (arraySize - 1), the loop is finished, 
# and the beqz instruction branches to the end of the loop.
# 
# Each time through the loop, the arrayElement is set to the arrayBase + counter, as
# this is the address of the current array element.  The value at this array address
# is added to the summation and stored in the memory at the address of the summation
# label, address 0.
#
# The program continues until the array has been processed, and the summation can be
# found in data memory at label summation (address 0)
#
clac
add arraySize
addi 1
sub counter
beqz endLoop  # Go to end when done

# calculate summation.  The summation is initially zero, but each time through the loop the product
# is augmented by the value of the multiplicand.  The product is then stored back to memory 
# for use on the next pass.
clac
add arrayBase
add counter
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
# result is in product (data address 0)

.data
.label summation  
    .number 0
.label counter 
    .number 0 
.label arraySize
     .number 5
.label arrayBase 
    .number 3
    .number 7
    .number 1
    .number 5
    .number 9

