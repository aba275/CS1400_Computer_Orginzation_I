 .data    
 	prompt1:.asciiz "Enter name: "
	msg:.asciiz "the name is: " 
	usrName: .space 20

	
.text

	#ask user for first number
	li $v0, 4     		#command for printing a string
   	la $a0, prompt1 	#loading the string to print into the argument to enable printing
    	syscall      		#executing the command
	
	li $v0, 8		#accepts individual from user
	la $a0, usrName
	li $a1, 10
	syscall 
	
	li  $v0, 4
	la $a0,msg
	syscall
	
	li $v0, 4
	la $a0, usrName
	syscall
