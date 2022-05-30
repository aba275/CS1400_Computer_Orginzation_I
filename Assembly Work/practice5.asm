
	# if(t1 == 10)
	# {t2 = 4}
	# else
	# {t2=3}

.text

main:
	add $t1, $zero, 10
	
	beq $t1, 10, myLabel	# if t1 == 10 jump to label
	li $t2, 3 		# else condition
	j exit
	
myLabel:
	li $t2, 4		# if condition

exit:
