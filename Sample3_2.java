public class Sample3_2 {                                      // 宣告公開類別 Sample3_2（檔名需與類別同名）
    public static void main(String[] args) {                  // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car(1234, 20.5);                      // 使用具名建構子一次設定「車號」與「汽油量」，避免後設置遺漏
        System.out.printf("車號是 %d%n", car1.getNum());      // 透過 getter 取得車號並以格式化輸出（%n 為跨平台換行）
        System.out.printf("汽油量是 %.1f 公升%n", car1.getGas()); // 透過 getter 取得汽油量，以 1 位小數顯示更易讀
        // System.out.println(car1);                          // 若需要完整摘要，可取消註解使用 toString()
    }                                                        // main 方法結束
}                                                            // 類別 Sample3_2 結束

final class Car {                                            // 宣告 Car 類別並標示為 final：避免被繼承以維持簡單封裝
    private int num;                                         // 私有欄位：車號（使用者可透過方法安全存取）
    private double gas;                                      // 私有欄位：汽油量（以公升為單位）

    public Car() {                                           // 無參數建構子：提供預設值以利快速建立
        this(0, 0.0);                                        // 委派到完整建構子，集中初始化邏輯
    }                                                        // 無參數建構子結束

    public Car(int num, double gas) {                        // 完整建構子：建立時即檢查與設定必要屬性
        setNum(num);                                         // 使用 setter 進行輸入驗證後設定車號
        setGas(gas);                                         // 使用 setter 進行輸入驗證後設定汽油量
    }                                                        // 完整建構子結束

    public int getNum() {                                    // 取得車號（唯讀介面）
        return num;                                          // 回傳車號
    }                                                        // getNum 方法結束

    public double getGas() {                                 // 取得汽油量（唯讀介面）
        return gas;                                          // 回傳汽油量
    }                                                        // getGas 方法結束

    public void setNum(int num) {                            // 設定車號（含基本驗證）
        if (num < 0) {                                       // 防呆：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數"); // 若不合法則拋出參數例外，避免物件進入不一致狀態
        }                                                    // 檢查結束
        this.num = num;                                      // 指派通過驗證的車號
    }                                                        // setNum 方法結束

    public void setGas(double gas) {                         // 設定汽油量（含基本驗證）
        if (gas < 0.0) {                                     // 防呆：汽油量不可為負
            throw new IllegalArgumentException("汽油量不得為負數"); // 若不合法則拋出參數例外
        }                                                    // 檢查結束
        this.gas = gas;                                      // 指派通過驗證的汽油量
    }                                                        // setGas 方法結束

    @Override                                                // 覆寫 Object.toString()：提供友善的摘要輸出
    public String toString() {                               // toString 方法：回傳可讀性佳的物件資訊
        return String.format("Car{num=%d, gas=%.2f}", num, gas); // 使用格式化字串輸出車號與汽油量（兩位小數）
    }                                                        // toString 方法結束
}                                                            // 類別 Car 結束
