.data
Msg1: .asciiz "Etnter intP: "
Msg2: .asciiz "Enter intQ: "
Msg3: .asciiz "Enter intR: "
Msg4: .asciiz "the smallest number is: "

.text
     # print message
 	li $v0, 4
 	la $a0, Msg1
 	syscall

 	# prompt for int
 	li $v0, 5
 	syscall

 	# store first int in $t0
 	move $t0, $v0

 	# print message
 	li $v0, 4
 	la $a0, Msg2
 	syscall
	
 	# enter int
 	li $v0, 5
 	syscall

 	# first int in $t1
 	move $t1, $v0

 	# Print message
 	li $v0, 4
 	la $a0, Msg3
 	syscall

 	# prompt for int
 	li $v0, 5
 	syscall

 	# first int in $t0
 	move $t2, $v0

 	# evaluate the smallest Number
 	blt $t0, $t1, L0
 	blt $t1, $t0, L3

 	li, $v0, 10
 	syscall

 	L0:
	blt $t0, $t2, L2
	blt $t2, $t0, L3

	 L2:
	li $v0, 1
	move $a0, $t0
	syscall
	li, $v0, 10
	syscall
	
	 L3:
	blt $t1, $t2, L4
	blt $t2, $t1, L5

	 L4:
	li $v0, 1
	move $a0, $t1
	syscall
	li, $v0, 10
	syscall

 	L5:
	li $v0, 1
	move $a0, $t2
	syscall
	li, $v0, 10
	syscall


 	li, $v0, 10
 	syscall

