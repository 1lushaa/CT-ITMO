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

  not_gate not1(a, not_a);
  not_gate not2(b, not_b);

  and_gate and1(not_a, b, and_not_a);
  and_gate and2(a, not_b, and_not_b);

  or_gate or1(and_not_a, and_not_b, out);
endmodule

module alu_adder(a, b, c_in, c_out, s);
  input wire a;
  input wire b;
  input wire c_in;
  output wire c_out;
  output wire s;

  xor_gate xor_gate1(a, b, one);
  xor_gate xor_gate2(one, c_in, s);
  and_gate and_gate1(a, b, c);
  and_gate and_gate2(one, c_in, c2);
  or_gate or_gate1(c, c2, c_out);
endmodule


module alu_sum(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  supply0 gnd;

  alu_adder adder1(a[0], b[0], gnd, cout1, res[0]);
  alu_adder adder2(a[1], b[1], cout1, cout2, res[1]);
  alu_adder adder3(a[2], b[2], cout2, cout3, res[2]);
  alu_adder adder4(a[3], b[3], cout3, cout4, res[3]);
endmodule

module alu_and(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  and_gate and1(a[0], b[0], res[0]);
  and_gate and2(a[1], b[1], res[1]);
  and_gate and3(a[2], b[2], res[2]);
  and_gate and4(a[3], b[3], res[3]);
endmodule

module alu_not_and(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  and_gate and1(a[0], b[0], andwire0);
  and_gate and2(a[1], b[1], andwire1);
  and_gate and3(a[2], b[2], andwire2);
  and_gate and4(a[3], b[3], andwire3);

  not_gate not1(andwire0, res[0]);
  not_gate not2(andwire1, res[1]);
  not_gate not3(andwire2, res[2]);
  not_gate not4(andwire3, res[3]);
endmodule

module alu_or(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  or_gate or1(a[0], b[0], res[0]);
  or_gate or2(a[1], b[1], res[1]);
  or_gate or3(a[2], b[2], res[2]);
  or_gate or4(a[3], b[3], res[3]);
endmodule

module alu_not_or(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  or_gate or1(a[0], b[0], orwire0);
  or_gate or2(a[1], b[1], orwire1);
  or_gate or3(a[2], b[2], orwire2);
  or_gate or4(a[3], b[3], orwire3);

  not_gate not1(orwire0, res[0]);
  not_gate not2(orwire1, res[1]);
  not_gate not3(orwire2, res[2]);
  not_gate not4(orwire3, res[3]);
endmodule

module alu_minus(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  supply1 pwr;

  not_gate not1(b[0], notb0);
  not_gate not2(b[1], notb1);
  not_gate not3(b[2], notb2);
  not_gate not4(b[3], notb3);

  alu_adder half_adder1(a[0], notb0, pwr, cout1, res[0]);
  alu_adder half_adder2(a[1], notb1, cout1, cout2, res[1]);
  alu_adder half_adder3(a[2], notb2, cout2, cout3, res[2]);
  alu_adder half_adder4(a[3], notb3, cout3, cout4, res[3]);
endmodule

module alu_slt(a, b, res);
  input [3:0] a, b;
  output [3:0] res;
  wire [3:0] min;

  wire [3:0] minus;

  supply1 pwr;

  alu_minus alu_min(a, b, minus);

  not_gate not01(minus[3], notmin);
  not_gate not02(a[3], not1);
  not_gate not03(b[3], not2);

  and_gate and01(a[3], not2, and1);
  and_gate and02(b[3], not1, and2);

  not_gate not04(and1, not3);
  not_gate not05(and2, not4);

  and_gate and11(not4, pwr, and11out);
  and_gate and12(notmin, and1, and12out);
  and_gate and111(and11out, and12out, alu_and1);

  and_gate and21(not4, pwr, and21out);
  and_gate and22(minus[3], not3, and22out);
  and_gate and222(and21out, and22out, alu_and2);

  and_gate and31(not4, pwr, and31out);
  and_gate and32(minus[3], not2, and32out);
  and_gate and333(and31out, and32out, alu_and3);

  or_gate or_gate41(alu_and1, alu_and2, alu_or);
  or_gate or_gate42(alu_and3, alu_or, ans);

  assign res[3] = 0;
  assign res[2] = 0;
  assign res[1] = 0;
  assign res[0] = ans;
endmodule

module alu_undef(a, b, res);
  input [3:0] a, b;
  output [3:0] res;

  assign res[3] = 0;
  assign res[2] = 0;
  assign res[1] = 0;
  assign res[0] = 0;
endmodule

module alu_mux(control, in, out);
input [2:0] control;
input [7:0] in;
output out;

not_gate not1(control[2], notA);
not_gate not2(control[1], notB);
not_gate not3(control[0], notC);

or_gate or0(andwire0, andwire1, orwire1);
or_gate or1(orwire1, andwire2, orwire2);
or_gate or2(orwire2, andwire3, orwire3);
or_gate or3(orwire3, andwire4, orwire4);
or_gate or4(orwire4, andwire5, orwire5);
or_gate or5(orwire5, andwire6, orwire6);
or_gate or6(orwire6, andwire7, out);

and_gate and0(andwire01, notC, andwire0);
and_gate and1(andwire11, control[0], andwire1);
and_gate and2(andwire21, notC, andwire2);
and_gate and3(andwire31, control[0], andwire3);
and_gate and4(andwire41, notC, andwire4);
and_gate and5(andwire51, control[0], andwire5);
and_gate and6(andwire61, notC, andwire6);
and_gate and7(andwire71, control[0], andwire7);

and_gate and00(in[0], notA, andwire00);
and_gate and01(andwire00, notB, andwire01);

and_gate and10(in[1], notA, andwire10);
and_gate and11(andwire10, notB, andwire11);

and_gate and20(in[2], notA, andwire20);
and_gate and21(andwire20, control[1], andwire21);

and_gate and30(in[3], notA, andwire30);
and_gate and31(andwire30, control[1], andwire31);

and_gate and40(in[4], control[2], andwire40);
and_gate and41(andwire40, notB, andwire41);

and_gate and50(in[5], control[2], andwire50);
and_gate and51(andwire50, notB, andwire51);

and_gate and60(in[6], control[2], andwire60);
and_gate and61(andwire60, control[1], andwire61);

and_gate and70(in[7], notA, andwire70);
and_gate and71(andwire70, notB, andwire71);
endmodule


module alu(a, b, control, res);
  input [3:0] a, b; // Операнды
  input [2:0] control; // Управляющие сигналы для выбора операции
  output [3:0] res; // Результат

  wire [3:0] andres;
  wire [3:0] notandres;
  wire [3:0] orres;
  wire [3:0] notorres;
  wire [3:0] sumres;
  wire [3:0] minusres;
  wire [3:0] sltres;
  wire [3:0] unusedres;

  alu_and alu_and1(a, b, andres);
  alu_not_and alu_not_and1(a, b, notandres);
  alu_or alu_or1(a, b, orres);
  alu_not_or alu_not_or1(a, b, notorres);
  alu_sum alu_sum1(a, b, sumres);
  alu_minus alu_minus1(a, b, minusres);
  alu_slt alu_slt1(a, b, sltres);
  alu_undef alu_unused1(a, b, unusedres);

  wire [7:0] in1;
  wire [7:0] in2;
  wire [7:0] in3;
  wire [7:0] in4;

  assign in1[7] = unusedres[3];
  assign in1[6] = sltres[3];
  assign in1[5] = minusres[3];
  assign in1[4] = sumres[3];
  assign in1[3] = notorres[3];
  assign in1[2] = orres[3];
  assign in1[1] = notandres[3];
  assign in1[0] = andres[3];

  assign in2[7] = unusedres[2];
  assign in2[6] = sltres[2];
  assign in2[5] = minusres[2];
  assign in2[4] = sumres[2];
  assign in2[3] = notorres[2];
  assign in2[2] = orres[2];
  assign in2[1] = notandres[2];
  assign in2[0] = andres[2];

  assign in3[7] = unusedres[1];
  assign in3[6] = sltres[1];
  assign in3[5] = minusres[1];
  assign in3[4] = sumres[1];
  assign in3[3] = notorres[1];
  assign in3[2] = orres[1];
  assign in3[1] = notandres[1];
  assign in3[0] = andres[1];

  assign in4[7] = unusedres[0];
  assign in4[6] = sltres[0];
  assign in4[5] = minusres[0];
  assign in4[4] = sumres[0];
  assign in4[3] = notorres[0];
  assign in4[2] = orres[0];
  assign in4[1] = notandres[0];
  assign in4[0] = andres[0];

  alu_mux mux1(control, in1, res[3]);
  alu_mux mux2(control, in2, res[2]);
  alu_mux mux3(control, in3, res[1]);
  alu_mux mux4(control, in4, res[0]);
endmodule

module d_latch(clk, d, we, q);
  input clk; // Сигнал синхронизации
  input d; // Бит для записи в ячейку
  input we; // Необходимо ли перезаписать содержимое ячейки

  output reg q; // Сама ячейка
  // Изначально в ячейке хранится 0
  initial begin
    q <= 0;
  end
  // Значение изменяется на переданное на спаде сигнала синхронизации
  always @ (negedge clk) begin
    // Запись происходит при we = 1
    if (we) begin
      q <= d;
    end
  end
endmodule

module register_file(clk, rd_addr, we_addr, we_data, rd_data, we);
  input clk; // Сигнал синхронизации
  input [1:0] rd_addr, we_addr; // Номера регистров для чтения и записи
  input [3:0] we_data; // Данные для записи в регистровый файл
  input we; // Необходимо ли перезаписать содержимое регистра
  output [3:0] rd_data; // Данные, полученные в результате чтения из регистрового файла

  supply0 gnd;
  supply1 pwr;

  wire [15:0] check;
  wire [3:0] id;
  wire [15:0] r;
  wire [3:0] to_r;
  wire [3:0] wr;

  not_gate not01(we_addr[1], notw1);
  not_gate not02(rd_addr[1], notr1);
  not_gate not11(we_addr[0], notw0);
  not_gate not12(rd_addr[0], notr0);

  and_gate and01(we_addr[1], we_addr[0], id[3]);
  and_gate and02(notw1, we_addr[0], id[2]);
  and_gate and03(we_addr[1], notw0, id[1]);
  and_gate and04(notw1, notw0, id[0]);

  and_gate and11(rd_addr[1], rd_addr[0], to_r[3]);
  and_gate and12(notr1, rd_addr[0], to_r[2]);
  and_gate and13(rd_addr[1], notr0, to_r[1]);
  and_gate and14(notr1, notr0, to_r[0]);

  and_gate and21(id[3], we, wr[3]);
  and_gate and22(id[2], we, wr[2]);
  and_gate and23(id[1], we, wr[1]);
  and_gate and24(id[0], we, wr[0]);

  d_latch d01(clk, we_data[3], wr[3], check[15]);
  d_latch d02(clk, we_data[2], wr[3], check[14]);
  d_latch d03(clk, we_data[1], wr[3], check[13]);
  d_latch d04(clk, we_data[0], wr[3], check[12]);

  d_latch d11(clk, we_data[3], wr[2], check[11]);
  d_latch d12(clk, we_data[2], wr[2], check[10]);
  d_latch d13(clk, we_data[1], wr[2], check[9]);
  d_latch d14(clk, we_data[0], wr[2], check[8]);

  d_latch d21(clk, we_data[3], wr[1], check[7]);
  d_latch d22(clk, we_data[2], wr[1], check[6]);
  d_latch d23(clk, we_data[1], wr[1], check[5]);
  d_latch d24(clk, we_data[0], wr[1], check[4]);

  d_latch d31(clk, we_data[3], wr[0], check[3]);
  d_latch d32(clk, we_data[2], wr[0], check[2]);
  d_latch d33(clk, we_data[1], wr[0], check[1]);
  d_latch d34(clk, we_data[0], wr[0], check[0]);

  and_gate and31(check[15], to_r[3], r[15]);
  and_gate and32(check[14], to_r[3], r[14]);
  and_gate and33(check[13], to_r[3], r[13]);
  and_gate and34(check[12], to_r[3], r[12]);

  and_gate and41(check[11], to_r[2], r[11]);
  and_gate and42(check[10], to_r[2], r[10]);
  and_gate and43(check[9], to_r[2], r[9]);
  and_gate and44(check[8], to_r[2], r[8]);

  and_gate and51(check[7], to_r[1], r[7]);
  and_gate and52(check[6], to_r[1], r[6]);
  and_gate and53(check[5], to_r[1], r[5]);
  and_gate and54(check[4], to_r[1], r[4]);

  and_gate and61(check[3], to_r[0], r[3]);
  and_gate and62(check[2], to_r[0], r[2]);
  and_gate and63(check[1], to_r[0], r[1]);
  and_gate and64(check[0], to_r[0], r[0]);

  or_gate or01(r[15], r[11], orres1);
  or_gate or02(orres1, r[7], orres2);
  or_gate or03(orres2, r[3], rd_data[3]);

  or_gate or11(r[14], r[10], orres3);
  or_gate or12(orres3, r[6], orres4);
  or_gate or13(orres4, r[2], rd_data[2]);

  or_gate or21(r[13], r[9], orres5);
  or_gate or22(orres5, r[5], orres6);
  or_gate or23(orres6, r[1], rd_data[1]);

  or_gate or31(r[12], r[8], orres7);
  or_gate or32(orres7, r[4], orres8);
  or_gate or33(orres8, r[0], rd_data[0]);
endmodule

module counter(clk, addr, control, immediate, data);
  input clk; // Сигнал синхронизации
  input [1:0] addr; // Номер значения счетчика которое читается или изменяется
  input [3:0] immediate; // Целочисленная константа, на которую увеличивается/уменьшается значение счетчика
  input control; // 0 - операция инкремента, 1 - операция декремента

  output [3:0] data; // Данные из значения под номером addr, подающиеся на выход

  supply1 pwr;
  supply0 gnd;

  wire[3:0] we;
  wire[3:0] to_out;
  wire[2:0] choose;

  assign choose[2] = pwr;
  assign choose[1] = gnd;
  assign choose[0] = control;

  register_file file(clk, addr, addr, we, to_out, pwr);

  alu alu1(to_out, immediate, choose, we);

  assign data[3] = to_out[3];
  assign data[2] = to_out[2];
  assign data[1] = to_out[1];
  assign data[0] = to_out[0];
endmodule
