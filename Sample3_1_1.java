import java.util.Objects;                          // 匯入 Objects：提供 null 檢查等常用工具方法，便於參數驗證

public class Sample3_1_1 {                         // 宣告公開類別 Sample3_1_1（檔名需與類別同名）
    public static void main(String[] args) {       // 主程式進入點（JVM 從這裡開始執行）
        Car car1 = new Car("Toyota", "Corolla", 2020, 15000); // 建立 Car 物件並同時指定品牌、車款、年份與里程
        car1.honk();                                // 呼叫喇叭方法，示範物件行為
        car1.drive(120);                            // 模擬行駛 120 公里，更新里程數
        System.out.println(car1);                   // 透過 toString() 輸出車輛資訊（品牌/車款/年份/里程）
    }                                               // main 方法結束
}                                                   // 類別 Sample3_1_1 結束

class Car {                                        // 宣告 Car 類別（對應一台車的資料與行為）
    private final String brand;                    // 私有成員：品牌（不可變，保障封裝）
    private final String model;                    // 私有成員：車款（不可變，保障封裝）
    private final int year;                        // 私有成員：年份（不可變，建立後不再更改）
    private int mileage;                           // 私有成員：里程數（可變，會隨行駛而累加）

    public Car() {                                  // 無參數建構子：提供預設值以方便快速建立物件
        this("Unknown", "Unknown", 0, 0);          // 透過 this(...) 委派到完整建構子，集中初始化邏輯
    }                                               // 無參數建構子結束

    public Car(String brand, String model, int year, int mileage) { // 完整建構子：建立物件時即給定所有屬性
        this.brand = Objects.requireNonNull(brand, "brand 不可為 null"); // 檢查品牌不為 null，避免後續 NPE
        this.model = Objects.requireNonNull(model, "model 不可為 null"); // 檢查車款不為 null
        if (year < 0) {                             // 檢查年份是否小於 0（不合理的年份）
            throw new IllegalArgumentException("year 必須為非負整數"); // 拋出非法參數例外，阻止建立不合理物件
        }                                           // 年份檢查結束
        if (mileage < 0) {                          // 檢查里程是否小於 0（不合理的里程）
            throw new IllegalArgumentException("mileage 必須為非負整數"); // 拋出非法參數例外
        }                                           // 里程檢查結束
        this.year = year;                           // 指派年份到成員變數
        this.mileage = mileage;                     // 指派里程到成員變數
    }                                               // 完整建構子結束

    public void drive(int km) {                     // 成員方法：行駛指定公里數並累加到里程
        if (km <= 0) {                              // 防呆：若輸入公里數 <= 0 則不做任何事
            return;                                  // 直接返回，避免負值導致里程回退
        }                                           // 防呆檢查結束
        mileage += km;                              // 將有效公里數累加到里程
    }                                               // drive 方法結束

    public void honk() {                            // 成員方法：按喇叭（示範物件行為）
        System.out.println("嗶嗶！");               // 輸出喇叭聲（可依需求換成音效或其他行為）
    }                                               // honk 方法結束

    public String getBrand() {                      // 取得品牌（唯讀存取器，維持封裝）
        return brand;                               // 回傳品牌字串
    }                                               // getBrand 方法結束

    public String getModel() {                      // 取得車款（唯讀存取器）
        return model;                               // 回傳車款字串
    }                                               // getModel 方法結束

    public int getYear() {                          // 取得年份（唯讀存取器）
        return year;                                // 回傳年份整數
    }                                               // getYear 方法結束

    public int getMileage() {                       // 取得目前里程（唯讀存取器）
        return mileage;                             // 回傳里程整數
    }                                               // getMileage 方法結束

    @Override                                       // 覆寫 Object.toString() 以提供更友善的顯示字串
    public String toString() {                      // toString 方法：描述車輛的摘要資訊
        return String.format("%s %s (%d) - 里程 %d 公里", // 使用格式化字串整齊輸出
                             brand, model, year, mileage); // 依序代入品牌、車款、年份與里程
    }                                               // toString 方法結束
}                                                   // 類別 Car 結束

