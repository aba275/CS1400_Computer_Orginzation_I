.data
	x : .word 89
	y : .word 67
	a : .word 0
.text
	lw $s0,x 		#loading x
	lw $s1,y 		#loading y

	sub $s2,$s0,$s1 	#getting x - y
	div $s2,$s2,4 		#getting (x - y)/4
	mfhi $s2 		#getting (x - y)%4
	sw $s2,a 		# a = (x - y)%4