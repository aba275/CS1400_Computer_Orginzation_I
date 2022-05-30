
	# if(t == 10 and $t2 >= 5)
	# {t2 = 4}
	# else
	# {t3=12}

.text

main:
	add $t1, $zero, 0
	li $t2, 5
	
	#Control Structure
	beqz $t1, if1		# if t1 == 10 jump to label
	j else
	
if1:
	bge $t2, 5, if2
	j else
	
if2:
	li $t3, 4		# if condition
	
else:

	li $t3, 12 		# else condition
	j end
	
end:
