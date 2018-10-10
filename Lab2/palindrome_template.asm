	.data
a:
	11111
	.text
main:
	load %x0, $a, %x3
	load %x0, $a, x4
	addi %x0, 0, %x5
loop:
	divi %x4, 10, %x4
	muli %x5, 10, %x5
	add %x5, %x31, %x5
	beq %x4, 0, success
	jmp loop
success:
	beq %x5, %x3, endl
	subi %x0, 1, %x10
	end
endl:
	addi %x0, 1, %x10
	end
