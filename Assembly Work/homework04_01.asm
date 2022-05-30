.data
	msg1: .asciiz "Enter your first number: "
	msg2: .asciiz "\nEnter your second number: "
	result1: .asciiz "\nThe result is (the if condition satisfied): "
	result2: .asciiz "\nThe result is (the else condition satisfied): "

.text
        main:
		# $t1-to hold first int 'a'
                # $t2-to hold second int 'b' 
                # $t3- used to hold the result of if/else statement 'c'
    
                # set $t3=10
                li $t3 10
                
                # First number
                li $v0,4     # syscall to print string
                la $a0,msg1  # string to print
                syscall

                li $v0,5     # read int
                syscall
                move $t1,$v0 # move int to read into $t1

                # Second number
                li $v0,4     # print string
                la $a0,msg2  # address of string to print
                syscall

                li $v0,5     # read int
                syscall
                move $t2,$v0 # move int into read $t1
                
                beq $t1, $t2, IFSTAT # if its condition true then jump 'IFSTAT'
                
                # Else 
                Add $t3,$t2,$t3 # compute the addition
                
                li $v0,4        # print string
                la $a0,result2  # address of string to print
                syscall

                # Print result
                li $v0,1        #  print result
                move $a0,$t3    # move result to $a0
                syscall

                # end program
                li $v0,10
                syscall
        
        # If statement
        IFSTAT:
                Add $t3,$t1,$t3 # compute addition operator            
                li $v0,4        # syscall to print string
                la $a0,result1  # address of string to print
                syscall

                # Print result
                li $v0,1        # print result
                move $a0,$t3    # move result to $a0
                syscall
