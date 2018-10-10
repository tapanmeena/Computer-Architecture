	.data
a:
	5
	6
	30
	24
	10
	7
n:
	6
number:
	88
	.text
main:
	add %x2, %x0, %x0
	load %x0, $n, %x6
	load %x0, $number, %x5
loop:
	load %x2, $a, %x4
	beq %x4, %x5, success
	addi %x2, 1, %x2
	bgt %x2, %x6, endl
	jmp loop
success:
	end
endl:
	sub %x2, %x2, %x2
	subi %x2, 1, %x2
	end