	.data
a:
	10
b:
	20
	.text
main:
	load %x0, $a, %x5
	addi %x0, 10, %x3
	add %x0, %x5, %x6
	end
