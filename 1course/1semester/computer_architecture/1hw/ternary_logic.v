module and_gate(a, b, out);
  input wire a, b;
  output wire out;

  wire nand_out;

  nand_gate nand_gate1(a, b, nand_out);
  not_gate not_gate1(nand_out, out);
endmodule

module nand_gate(a, b, out);
  input wire a, b;
  output out;

  supply1 pwr;
  supply0 gnd;

  wire nmos1_out;

  pmos pmos1(out, pwr, a);
  pmos pmos2(out, pwr, b);

  nmos nmos1(nmos1_out, gnd, b);
  nmos nmos2(out, nmos1_out, a);
endmodule

module not_gate(a, out);
  input wire a;
  output out;

  supply1 pwr;
  supply0 gnd;

  pmos pmos1(out, pwr, a);
  nmos nmos1(out, gnd, a);
endmodule

module or_gate(a, b, out);
  input wire a;
  input wire b;

  output out;
  wire not_a;
  wire not_b;
  wire and_out;

  not_gate not_gate_a(a, not_a);
  not_gate not_gate_b(b, not_b);

  and_gate res(not_a, not_b, and_out);

  not_gate not_gate1(and_out, out);
endmodule

module xor_gate(a, b, out);
  input wire a;
  input wire b;

  output out;
  wire not_a;
  wire not_b;
  wire and_not_a;
  wire and_not_b;

  not_gate not1(a, not_a);
  not_gate not2(b, not_b);

  and_gate and1(not_a, b, and_not_a);
  and_gate and2(a, not_b, and_not_b);

  or_gate or1(and_not_a, and_not_b, out);
endmodule

module ternary_min(a, b, out);
  input [1:0] a;
  input [1:0] b;

  wire first;
  wire second;
  wire third;
  wire fourth;

  output [1:0] out;

  and_gate and1(a[0], b[0], first);
  xor_gate xor1(a[1], b[1], second);
  or_gate or1(a[0], b[0], third);
  and_gate and2(second, third, fourth);

  and_gate first_out(a[1], b[1], out[1]);
  or_gate second_out(first, fourth, out[0]);
endmodule

module ternary_max(a, b, out);
  input [1:0] a;
  input [1:0] b;

  output [1:0] out;

  wire not_a1;
  wire not_b1;
  wire not_a0;
  wire not_b0;
  wire one;
  wire two;
  wire three;
  wire four;
  wire five;
  wire six;
  wire seven;
  wire eighth;
  wire nine;
  wire ten;
  wire eleven;
  wire twelve;
  wire thirteen;
  wire fourteen;
  wire fifteen;
  wire sixteen;
  wire seventeen;
  wire eighteen;
  wire nineteen;
  wire twenty;
  wire twentyone;
  wire twentytwo;
  wire twentythree;
  wire twentyfour;
  wire or_1;
  wire or_2;
  wire or_3;
  wire or_4;

  not_gate nota1(a[1], not_a1);
  not_gate nota0(a[0], not_a0);
  not_gate notb1(b[1], not_b1);
  not_gate notb0(b[0], not_b0);

  and_gate and1(not_a1, not_a0, one);
  and_gate and2(one, b[1], two);
  and_gate and3(two, not_b0, three);

  and_gate and4(not_a1, a[0], four);
  and_gate and5(four, b[1], five);
  and_gate and6(five, not_b0, six);

  and_gate and7(a[1], not_a0, seven);
  and_gate and8(seven, not_b1, eight);
  and_gate and9(eight, not_b0, nine);

  and_gate and10(a[1], not_a0, ten);
  and_gate and11(ten, not_b1, eleven);
  and_gate and12(eleven, b[0], twelve);

  and_gate and13(a[1], not_a0, thirteen);
  and_gate and14(thirteen, b[1], fourteen);
  and_gate and15(fourteen, not_b0, fifteen);

  and_gate and16(not_a1, not_a0, sixteen);
  and_gate and17(sixteen, not_b1, seventeen);
  and_gate and18(seventeen, b[0], eighteen);

  and_gate and19(not_a1, a[0], nineteen);
  and_gate and20(nineteen, not_b1, twenty);
  and_gate and21(twenty, not_b0, twentyone);

  and_gate and22(not_a1, a[0], twentytwo);
  and_gate and23(twentytwo, not_b1, twentythree);
  and_gate and24(twentythree, b[0], twentyfour);

  or_gate or1(three, six, or_1);
  or_gate or2(or_1, nine, or_2);
  or_gate or3(or_2, twelve, or_3);
  or_gate or4(or_3, fifteen, out[1]);
  or_gate or5(eighteen, twentyone, or_4);
  or_gate or6(or_4, twentyfour, out[0]);
endmodule

module ternary_any(a, b, out);
  input [1:0] a;
  input [1:0] b;

  output [1:0] out;

  wire one;
  wire two;
  wire three;
  wire four;
  wire five;
  wire six;
  wire seven;
  wire eight;
  wire nine;
  wire ten;
  wire eleven;
  wire twelve;
  wire thirteen;
  wire fourteen;
  wire fifteen;
  wire sixteen;
  wire eighteen;
  wire not_a0;
  wire not_b0;
  wire not_a1;
  wire not_b1;
  wire or_1;
  wire or_2;

  not_gate nota0(a[0], not_a0);
  not_gate nota1(a[1], not_a1);
  not_gate notb0(b[0], not_b0);
  not_gate notb1(b[1], not_b1);

  and_gate and1(not_a1, a[0], one);
  and_gate and2(one, b[1], two);
  and_gate and3(two, not_b0, three);

  and_gate and4(a[1], not_a0, four);
  and_gate and5(four, not_b1, five);
  and_gate and6(five, b[0], six);

  and_gate and7(a[1], not_a0, seven);
  and_gate and8(seven, b[1], eight);
  and_gate and9(eight, not_b0, nine);

  and_gate and10(not_a1, not_a0, ten);
  and_gate and11(ten, b[1], eleven);
  and_gate and12(eleven, not_b0, twelve);

  and_gate and13(not_a1, a[0], thirteen);
  and_gate and14(thirteen, not_b1, fourteen);
  and_gate and15(fourteen, b[0], fifteen);

  and_gate and16(a[1], not_a0, sixteen);
  and_gate and17(sixteen, not_b1, seventeen);
  and_gate and18(seventeen, not_b0, eighteen);

  or_gate or1(three, six, or_1);
  or_gate or2(or_1, nine, out[1]);
  or_gate or3(twelve, fifteen, or_2);
  or_gate or4(or_2, eighteen, out[0]);
endmodule

module ternary_consensus(a, b, out);
  input [1:0] a;
  input [1:0] b;

  output [1:0] out;

  wire one;
  wire two;
  wire three;
  wire four;
  wire five;
  wire six;
  wire seven;
  wire eight;
  wire not_a0;
  wire not_a1;
  wire not_b0;
  wire not_b1;

  not_gate nota0(a[0], not_a0);
  not_gate nota1(a[1], not_a1);
  not_gate notb0(b[0], not_b0);
  not_gate notb1(b[1], not_b1);

  or_gate or1(a[1], a[0], one);
  or_gate or2(one, b[1], two);
  or_gate or3(two, b[0], three);

  or_gate or4(not_a1, a[0], four);
  or_gate or5(four, not_b1, five);
  or_gate or6(five, b[0], six);

  and_gate and1(three, six, out[0]);
  and_gate and2(a[1], not_a0, seven);
  and_gate and3(seven, b[1], eight);
  and_gate and4(eight, not_b0, out[1]);
endmodule
