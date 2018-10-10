	.data
a:
	10
	.text
m:
	load %x0, $a, %x3
	blt %x0, 4, success
	divi %x3, 2, %x4
	addi %x0, 2, %x5
checker:
	bgt %x5, %x4, success
	div %x3, %x5, %x6
	beq %x31, 0, fail
	addi %x5, 1, %x5
	jmp checker
fail:
	subi %x0, 1, %x10
	end
success:
	addi %x0, 1, %x10
	end
