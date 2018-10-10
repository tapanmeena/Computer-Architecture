	.data
a:
	10
	.text
main:
	load %x0, $a, %x3
	divi %x3, 2, %x4
	add %x0, %x0, %x10
	beq %x31, 1, odd
	addi %x10, 1, %x10
	end
odd:
	subi %x10, 1, %x10
	end
