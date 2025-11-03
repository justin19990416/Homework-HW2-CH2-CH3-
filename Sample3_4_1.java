public class Sample3_4_1 {                                      // 宣告公開類別 Sample3_4_1（檔名需與類別同名）
    public static void main(String[] args) {                    // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car(1234, 20.5);                        // 以建構子一次設定車號與汽油量，避免先建後設造成遺漏

        car1.show();                                           // 呼叫 show() 顯示目前車輛狀態（車號與汽油量）
        car1.show();                                           // 再呼叫一次，示範方法可重複呼叫且結果一致（物件狀態未改變）
    }                                                           // main 方法結束
}                                                               // 類別 Sample3_4_1 結束

final class Car {                                               // 宣告 Car 類別並標示為 final（此範例不需被繼承，保持簡單）
    private int num;                                            // 私有欄位：車號（封裝資料，禁止外部直接改動）
    private double gas;                                         // 私有欄位：汽油量（單位：公升）

    public Car() {                                              // 無參數建構子：提供預設值以利快速建立物件
        this(0, 0.0);                                           // 委派至完整建構子，集中初始化邏輯（避免重複程式碼）
    }                                                           // 無參數建構子結束

    public Car(int num, double gas) {                           // 完整建構子：建立時即驗證並設定必要屬性
        setNum(num);                                            // 使用 setter 進行輸入驗證後設定車號
        setGas(gas);                                            // 使用 setter 進行輸入驗證後設定汽油量
    }                                                           // 完整建構子結束

    public int getNum() {                                       // 取得車號（唯讀介面，維持封裝）
        return num;                                             // 回傳車號
    }                                                           // getNum 方法結束

    public double getGas() {                                    // 取得汽油量（唯讀介面，維持封裝）
        return gas;                                             // 回傳汽油量
    }                                                           // getGas 方法結束

    public void setNum(int num) {                               // 設定車號（含基本參數驗證）
        if (num < 0) {                                          // 防呆：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數"); // 不合法則丟出參數例外，避免物件進入不一致狀態
        }                                                       // 驗證結束
        this.num = num;                                         // 指派通過驗證的車號
    }                                                           // setNum 方法結束

    public void setGas(double gas) {                            // 設定汽油量（含基本參數驗證）
        if (gas < 0.0) {                                        // 防呆：汽油量不可為負數
            throw new IllegalArgumentException("汽油量不得為負數"); // 不合法則丟出參數例外
        }                                                       // 驗證結束
        this.gas = gas;                                         // 指派通過驗證的汽油量
    }                                                           // setGas 方法結束

    public void show() {                                        // 顯示車輛狀態的方法（對外提供只讀檢視）
        System.out.printf("車號是 %d%n", num);                  // 使用格式化輸出顯示車號（%n 為跨平台換行）
        System.out.printf("汽油量是 %.1f 公升%n", gas);         // 使用格式化輸出顯示汽油量（保留 1 位小數並標示單位）
    }                                                           // show 方法結束

    @Override                                                   // 覆寫 Object.toString()：提供友善的摘要字串
    public String toString() {                                  // 轉為字串以便偵錯或記錄
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 回傳車輛資訊摘要（車號與汽油量）
    }                                                           // toString 方法結束
}                                                               // 類別 Car 結束

