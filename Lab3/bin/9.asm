	.data
a:
	11
	.text
main:
	load %0, $a, %x3
	divi %x3, 2, %x4
	beq %x31, %x3, even
	add %x0, %x0, %x7
	end
even:
	subi %x0, 1, %x10
	end
