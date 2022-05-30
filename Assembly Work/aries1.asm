

#data section
.data

	myMsg: .asciiz "Akiel Aries "

#code section
.text

# to print message
	li $v0 , 4	# 4-> system code for printing a string: li is load immediate
	la $a0, myMsg	# address of string to print; la is load address
	syscall 	# print the string (invoke system call 4 define above)
	
	li $v0 , 1	#1-> system code for printing a string
	la $a0 , 6612 	# address of string to pring
	syscall
