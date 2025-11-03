public class Sample3_3_1 {                                      // 宣告公開類別 Sample3_3（檔名需與類別同名）
    public static void main(String[] args) {                  // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car(1234, 20.5);                      // 以建構子一次設定車號與汽油量（避免先建後設造成遺漏）
        Car car2 = new Car(2345, 30.5);                      // 建立第二台車，同樣用建構子帶入初值

        printCar("car1", car1);                              // 呼叫輔助方法印出 car1 的資訊（避免重複程式碼）
        printCar("car2", car2);                              // 呼叫輔助方法印出 car2 的資訊
    }                                                        // main 方法結束

    private static void printCar(String label, Car car) {    // 私有輔助方法：統一輸出格式
        System.out.printf("%s車號是 %d%n", label, car.getNum());     // 以格式化輸出顯示車號（%n 為跨平台換行）
        System.out.printf("%s汽油量是 %.1f 公升%n", label, car.getGas()); // 以 1 位小數顯示汽油量（單位：公升）
    }                                                        // printCar 方法結束
}                                                            // 類別 Sample3_3 結束

final class Car {                                            // 宣告 Car 類別並標示為 final（避免被繼承以保持簡單封裝）
    private int num;                                         // 私有欄位：車號（license number）
    private double gas;                                      // 私有欄位：汽油量（單位：公升）

    public Car() {                                           // 無參數建構子：提供預設值以利快速建立物件
        this(0, 0.0);                                        // 委派至完整建構子集中初始化邏輯
    }                                                        // 無參數建構子結束

    public Car(int num, double gas) {                        // 完整建構子：建立時即驗證並設定必要屬性
        setNum(num);                                         // 經由 setter 做輸入驗證後設定車號
        setGas(gas);                                         // 經由 setter 做輸入驗證後設定汽油量
    }                                                        // 完整建構子結束

    public int getNum() {                                    // 取得車號（getter，維持封裝）
        return num;                                          // 回傳車號
    }                                                        // getNum 方法結束

    public double getGas() {                                 // 取得汽油量（getter，維持封裝）
        return gas;                                          // 回傳汽油量
    }                                                        // getGas 方法結束

    public void setNum(int num) {                            // 設定車號（含基本參數驗證）
        if (num < 0) {                                       // 檢查：車號不可為負值
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則丟出參數例外（防止物件進入不一致狀態）
        }                                                    // 檢查結束
        this.num = num;                                      // 指派合法車號
    }                                                        // setNum 方法結束

    public void setGas(double gas) {                         // 設定汽油量（含基本參數驗證）
        if (gas < 0.0) {                                     // 檢查：汽油量不可為負值
            throw new IllegalArgumentException("汽油量不得為負數"); // 不合法則丟出參數例外
        }                                                    // 檢查結束
        this.gas = gas;                                      // 指派合法汽油量
    }                                                        // setGas 方法結束

    @Override                                                // 覆寫 Object.toString() 以提供友善的字串表示
    public String toString() {                               // 將物件狀態轉為可讀字串
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 使用格式化輸出車號與汽油量
    }                                                        // toString 方法結束
}                                                            // 類別 Car 結束
