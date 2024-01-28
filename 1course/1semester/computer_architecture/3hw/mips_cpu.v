//ONLY FIRST PART OF THE TASK!!!

`include "util.v"

module mips_cpu(clk, pc, pc_new, instruction_memory_a, instruction_memory_rd, data_memory_a, data_memory_rd, data_memory_we, data_memory_wd,
                register_a1, register_a2, register_a3, register_we3, register_wd3, register_rd1, register_rd2);
  // сигнал синхронизации
  input clk;
  // текущее значение регистра PC
  inout [31:0] pc;
  // новое значение регистра PC (адрес следующей команды)
  output [31:0] pc_new;
  // we для памяти данных
  output data_memory_we;
  // адреса памяти и данные для записи памяти данных
  output [31:0] instruction_memory_a, data_memory_a, data_memory_wd;
  // данные, полученные в результате чтения из памяти
  inout [31:0] instruction_memory_rd, data_memory_rd;
  // we3 для регистрового файла
  output register_we3;
  // номера регистров
  output [4:0] register_a1, register_a2, register_a3;
  // данные для записи в регистровый файл
  output [31:0] register_wd3;
  // данные, полученные в результате чтения из регистрового файла
  inout [31:0] register_rd1, register_rd2;

  wire drain;

  wire [5:0] code = instruction_memory_rd[31:26];
  wire [4:0] rs = instruction_memory_rd[25:21];
  wire [4:0] rt = instruction_memory_rd[20:16];
  wire [4:0] rd = instruction_memory_rd[15:11];
  wire [15:0] imm = instruction_memory_rd[15:0];
  wire [5:0] func = instruction_memory_rd[5:0];
  wire [25:0] addr = instruction_memory_rd[25:0];

  reg mr1, mw1, bn1, be1, alu1, rd1, rw0, jump1, jal1, jr1;
  reg [2:0] aluop;

  always @* begin
  case (code)
    6'b100011: begin
      bn1 = 0; 
      be1 = 0; 
      mw1 = 0; 
      aluop = 3'b010; 
      jump1 = 0; 
      jr1 = 0;
      rw0 = 1; 
      rd1 = 0; 
      alu1 = 1; 
      mr1 = 1; 
      jal1 = 0;
    end
    6'b101011: begin
      bn1 = 0; 
      be1 = 0; 
      mw1 = 1; 
      aluop = 3'b010; 
      jump1 = 0; 
      jr1 = 0;
      rw0 = 0; 
      rd1 = 0; 
      alu1 = 1; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b000100: begin
      bn1 = 0;
      be1 = 1;
      mw1 = 0; 
      aluop = 3'b110; 
      jump1 = 0; 
      jr1 = 0;
      rw0 = 0; 
      rd1 = 0; 
      alu1 = 0; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b000000: begin
      begin
      case (func)
        6'b100000: aluop = 3'b010;
        6'b100010: aluop = 3'b110;
        6'b100100: aluop = 3'b000;
        6'b100101: aluop = 3'b001;
        6'b101010: aluop = 3'b111;
        6'b001000: begin 
          jr1= 1;
          rw0 = 0;
        end
      endcase
      end
      bn1 = 0;
      be1 = 0; 
      mw1 = 0;  
      jump1 = 0; 
      jr1 = 0;
      rw0 = 1; 
      rd1 = 1; 
      alu1 = 0; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b000101: begin
      bn1 = 1; 
      be1 = 0; 
      mw1 = 0; 
      aluop = 3'b110;
      jump1 = 0; 
      jr1 = 0;
      rw0 = 0; 
      rd1 = 0; 
      alu1 = 0; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b001000: begin
      bn1 = 0; 
      be1 = 0; 
      mw1 = 0; 
      aluop = 3'b010; 
      jump1 = 0; 
      jr1 = 0;
      rw0 = 1; 
      rd1 = 0; 
      alu1 = 1; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b001100: begin
      bn1 = 0; 
      be1 = 0; 
      mw1 = 0; 
      aluop = 3'b000; 
      jump1 = 0; 
      jr1 = 0;
      rw0 = 1; 
      rd1 = 0; 
      alu1 = 1; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b000010: begin
      bn1 = 0; 
      be1 = 0; 
      mw1 = 0;  
      jump1 = 1; 
      jr1 = 0;
      rw0 = 0; 
      rd1 = 0; 
      alu1 = 0; 
      mr1 = 0; 
      jal1 = 0;
    end
    6'b000011: begin
      bn1 = 0; 
      be1 = 0; 
      mw1 = 0;  
      jump1 = 1; 
      jr1 = 0;
      rw0 = 1; 
      rd1 = 0; 
      alu1 = 0; 
      mr1 = 0; 
      jal1 = 1;
    end
  endcase
  end

  wire [2:0] sum = 3'b010;
  wire [4:0] max5 = 5'b11111;
  wire zero;
  wire [31:0] firstOp = register_rd1;
  wire [31:0] const;
  sign_extend sign(imm, const);
  wire [31:0] newconst;
  shl_2 s(const, newconst);

  wire [4:0] regw;
  wire [31:0] res;
  wire [31:0] secondOp;
  mux2_32 mux3(register_rd2, const, alu1, secondOp);
  alu alu2(firstOp, secondOp, aluop, res, zero);
  wire invert = !zero;
  wire newz = be1 ? invert : zero;
  wire bc = zero ? bn1 : be1;
  reg src;
  always @(bc && newz) begin
    src = bc && newz;
  end

  assign register_a1 = rs;
  assign register_a2 = rt;
  mux2_5 mux1(rt, rd, rd1, regw);
  mux2_5 mux2(regw, max5, jal1, register_a3);

  wire [31:0] newwd3;
  assign data_memory_a = res;
  assign data_memory_we = mw1;
  mux2_32 mux4(res, data_memory_rd, mr1, newwd3);
  mux2_32 mux5(newwd3, pcvar1, jal1, register_wd3);
  assign register_we3 = rw0;
  assign data_memory_wd = register_rd2;

  wire [31:0] pcvar1;
  wire [31:0] pcvar2;
  wire [31:0] newpcvar1;
  wire [31:0] newpcvar2;
  alu alu3(pc, 4, sum, pcvar1, drain);
  alu alu4(pcvar1, newconst, sum, pcvar2, drain);
  mux2_32 mux7(pcvar1, pcvar2, src, newpcvar1);
  mux2_32 mux8(newpcvar1, end1, jump1, newpcvar2);
  mux2_32 mux9(newpcvar2, firstOp, jr1, pc_new);

  wire [31:0] jump2 = {6'b000000, addr}; 
  wire [31:0] end1;
  shl_2 shh(jump2, end1);
  assign instruction_memory_a = pc;
endmodule
