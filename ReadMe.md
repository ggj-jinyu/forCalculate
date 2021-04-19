# forCalculate

### 四则选择器
一个自动生成小学四则运算题目的命令行程序

### 说明：
* 自然数：0, 1, 2, …。
* 真分数：1/2, 1/3, 2/3, 1/4, 1’1/2, …。
* 运算符：+, −, ×, ÷。
* 括号：(, )。
* 等号：=。
* 分隔符：空格（用于四则运算符和等号前后）。
* 算术表达式：
  e = n | e1 + e2 | e1 − e2 | e1 × e2 | e1 ÷ e2 | (e),
  其中e, e1和e2为表达式，n为自然数或真分数。
* 四则运算题目：e = ，其中e为算术表达式。

### 需求：
1. 使用 -n 参数控制生成题目的个数，例如  Myapp.exe -n 10 将生成10个题目。
2. 使用 -r 参数控制题目中数值（自然数、真分数和真分数分母）的范围，例如  Myapp.exe -r 10
将生成10以内（不包括10）的四则运算题目。该参数可以设置为1或其他自然数。该参数必须给定，否则程序报错并给出帮助信息。

* tip:
    * 生成的题目中计算过程不能产生负数，也就是说算术表达式中如果存在形如e1− e2的子表达式，那么e1≥ e2。
    * 生成的题目中如果存在形如e1÷ e2的子表达式，那么其结果应是真分数。
    * 每道题目中出现的运算符个数不超过3个。
    * 程序一次运行生成的题目不能重复，即任何两道题目不能通过有限次交换+和×左右的算术表达式变换为同一道题目。例如，23 + 45 = 和45 + 23 = 是重复的题目，6 × 8 = 和8 × 6 = 也是重复的题目。3+(2+1)和1+2+3这两个题目是重复的，由于+是左结合的，1+2+3等价于(1+2)+3，也就是3+(1+2)，也就是3+(2+1)。但是1+2+3和3+2+1是不重复的两道题，因为1+2+3等价于(1+2)+3，而3+2+1等价于(3+2)+1，它们之间不能通过有限次交换变成同一个题目。
    * 生成的题目存入执行程序的当前目录下的Exercises.txt文件
    * 其中真分数在输入输出时采用如下格式，真分数五分之三表示为3/5，真分数二又八分之三表示为2’3/8。
    * 在生成题目的同时，计算出所有题目的答案，并存入执行程序的当前目录下的Answers.txt文件
    * 程序应能支持一万道题目的生成。

3. 程序支持对给定的题目文件和答案文件，判定答案中的对错并进行数量统计，输入参数如下：
    Myapp.exe -e <exercisefile>.txt -a <answerfile>.txt
  * 统计结果输出到文件Grade.txt，格式如下:
    * Correct: 5 (1, 3, 5, 7, 9)
    * Wrong: 5 (2, 4, 6, 8, 10)
  * 其中“:”后面的数字5表示对/错的题目的数量，括号内的是对/错题目的编号。为简单起见，假设输入的题目都是按照顺序编号的符合规范的题目。