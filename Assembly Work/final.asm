
	# if(a == 10)
	# {a=a+5}
	# else
	# {a=a-5}

.text

main:
	add $t1, $zero, 0
	li $t2, 5
	
	#Control Structure
	beqz $t1, if1
	j else
	
if1:
	bge $t2, 5, if2
	j else
	
if2:
	li $t3, 4		
	
else:

	li $t3, 12 		
	j end
	
end:
