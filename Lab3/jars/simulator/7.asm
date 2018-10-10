	.data
a:
	121
	.text
main:
	addi %x0, 0, %x7
	load %x0, $a, %x5
	load %x0, $a, %x3
loop:
	divi %x3, 10, %x4
	add %x4, %x0, %x3
	add %x7, %x31, %x7
	beq %x4, 0, check
	muli %x7, 10, %x7
	jmp loop
check:
	beq %x5, %x7, success
	subi %x0, 1, %x10
	end
success:
	addi %x0, 1, %x10
	end
