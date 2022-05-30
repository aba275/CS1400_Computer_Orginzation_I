.data
	msg1: .asciiz "Enter your first number: "
	msg2: .asciiz "\nEnter your second number: "
	result1: .asciiz "\nThe result is (the if condition satisfies): "
	result2: .asciiz "\nThe result is (the else condition satisfies): "
	
.text
        main:
                # $t1- to hold int 'a'
                # $t2- to hold int 'b' 
                # $t3- to hold result of if/else statement 'c'
                
                # First number
                li $v0,4     # syscall print string
                la $a0,msg1  # address of string to print
                syscall

                li $v0,5     # read int
                syscall
                move $t1,$v0 # move int to read into $t1

                # Second number
                li $v0,4     # syscall print string
                la $a0,msg2  # address of string to print
                syscall

                li $v0,5     # syscall read an int
                syscall
                move $t2,$v0 # move int to read into $t1
                
                # Branch clause
                slt $t0, $t1, $t2
                bnez $t0, ELSESTAT # if 'If' condition (a > b) is false then jump 'ELSESTAT'
                beqz $t2, ELSESTAT # if 'If' condition (b != 0) is false then jump 'ELSESTAT'
                
                # If clause
                Sub $t1,$t1,$t2 # compute addition operation
                
                move $t3,$t1    # move result to $t3
                
                li $v0,4        # print string
                la $a0,result1  # address of string to print
                syscall
                
                # Print result
                li $v0,1        # print result
                move $a0,$t3    # move result to $a0
                syscall

                # end program
                li $v0,10
                syscall
        
        # Else statement
        ELSESTAT:
                Add $t1,$t1,$t2 # compute addition operation
                
                move $t3,$t1    # move result to $t3
                
                li $v0,4        # print string
                la $a0,result2  # address of string to print
                syscall

                # Print result
                li $v0,1        # print result
                move $a0,$t3    # move result to $a0
                syscall
