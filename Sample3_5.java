public class Sample3_5 {                                       // 宣告公開類別 Sample3_5（檔名需與類別同名）
    public static void main(String[] args) {                   // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car(1234, 20.5);                        // 以建構子一次設定車號與汽油量（避免先建後設造成遺漏）
        car1.showInfo();                                       // 呼叫封裝好的對外方法，先印出提示再顯示詳細車輛資料
        // car1.setGas(30.0);                                  // （範例）若需更新汽油量可透過 setter；驗證由方法內負責
        // car1.showInfo();                                    // （範例）再次顯示更新後的車輛資料
    }                                                          // main 方法結束
}                                                              // 類別 Sample3_5 結束

final class Car {                                              // 宣告 Car 類別並標示為 final（此範例不需被繼承，維持簡潔）
    private int num;                                           // 私有欄位：車號（封裝資料，避免外部直接修改）
    private double gas;                                        // 私有欄位：汽油量（單位：公升，允許小數）

    public Car() {                                             // 無參數建構子：提供預設值方便快速建立物件
        this(0, 0.0);                                          // 透過 this(...) 委派到完整建構子，集中初始化邏輯
    }                                                          // 無參數建構子結束

    public Car(int num, double gas) {                          // 完整建構子：建立物件時立即給定車號與汽油量
        setNum(num);                                           // 使用 setter 設定車號並進行基本驗證（避免不合理值）
        setGas(gas);                                           // 使用 setter 設定汽油量並進行基本驗證（避免負值）
    }                                                          // 完整建構子結束

    public int getNum() {                                      // 取得車號的 getter（維持封裝）
        return num;                                            // 回傳目前的車號
    }                                                          // getNum 方法結束

    public double getGas() {                                   // 取得汽油量的 getter（維持封裝）
        return gas;                                            // 回傳目前的汽油量
    }                                                          // getGas 方法結束

    public void setNum(int num) {                              // 設定車號的 setter（含基本參數驗證）
        if (num < 0) {                                         // 防呆：車號不可為負數
            throw new IllegalArgumentException("車號不得為負數"); // 若不合法則拋出參數例外，避免物件進入不一致狀態
        }                                                      // 驗證結束
        this.num = num;                                        // 指派通過驗證的車號到欄位
    }                                                          // setNum 方法結束

    public void setGas(double gas) {                           // 設定汽油量的 setter（含基本參數驗證）
        if (gas < 0.0) {                                       // 防呆：汽油量不可為負數
            throw new IllegalArgumentException("汽油量不得為負數"); // 若不合法則拋出參數例外
        }                                                      // 驗證結束
        this.gas = gas;                                        // 指派通過驗證的汽油量到欄位
    }                                                          // setGas 方法結束

    public void show() {                                       // 顯示車輛詳細資料的方法（對內細節，可被其他方法呼叫）
        System.out.printf("車號是 %d%n", this.num);            // 使用格式化輸出顯示車號（%n 為跨平台換行）
        System.out.printf("汽油量是 %.1f 公升%n", this.gas);    // 使用格式化輸出顯示汽油量（保留 1 位小數與單位）
    }                                                          // show 方法結束

    public void showInfo() {                                   // 封裝對外的顯示介面（取代原本的 showCar0，命名更語義化）
        System.out.println("開始顯示車子資料");                 // 先印出提示訊息，提高可讀性與使用者體驗
        this.show();                                           // 呼叫內部的 show() 印出實際資料（避免重複程式碼）
    }                                                          // showInfo 方法結束

    @Override                                                  // 覆寫 Object.toString()：提供友善的物件字串表示
    public String toString() {                                 // 將物件狀態轉為可讀字串（便於除錯與記錄）
        return String.format("Car{num=%d, gas=%.1f}", num, gas); // 回傳包含車號與汽油量的摘要字串
    }                                                          // toString 方法結束
}                                                              // 類別 Car 結束

