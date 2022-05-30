.data
n1:.word 8
n2:.word 3
msg:.asciiz "the sum is: "
msg2:.asciiz "the difference is: "

.text
	

	lw $t0, n1
	lw $t1, n2
	
	#Sum
	add $t3, $t0, $t1
	li $v0, 1
	move $a0, $t3
	la $t8, 1
	syscall
	
	#Difference
	sub $t3, $t0, $t1
	li $v0, 1
	move $a0, $t3
	la $t9, 1
	syscall
