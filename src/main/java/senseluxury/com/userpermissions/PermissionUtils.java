package senseluxury.com.userpermissions;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay_zhao
 * on 2017/2/27.
 */

public class PermissionUtils {
    private Context mContext;
    private static PermissionUtils instance = new PermissionUtils();

    private PermissionUtils() {
    }

    public static PermissionUtils getInstance(Context context) {
        instance.setContext(context);
        return instance;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context.getApplicationContext();
    }

    /**
     * 检测权限是否授予 返回true表示需要申请授权 false则表示已经授权
     * @param per
     * @return
     */
    public boolean checkPermission(String per) {
        return ContextCompat.checkSelfPermission(mContext,per) == PackageManager.PERMISSION_DENIED;
    }

    /**
     * 检查权限集合 返回true表示需要申请权限 false则表示已全部授权
     * @param pers
     * @return
     */
    public boolean checkPermissions(String... pers) {
        for (String permission : pers){
            if (checkPermission(permission)){
                return true;
            }
        }
        return false;
    }

    /**
     * 检查权限集合 返回没有授权的权限字符串集合
     * @param pers
     * @return
     */
    public List<String> checkPermission(String... pers){
        List<String> list = new ArrayList<>();
        for (String permission:pers){
            if (checkPermission(permission)){
                list.add(permission);
            }
        }
        return list;
    }

    /**
     * 检查权限集合 返回没有授权的权限字符串集合
     * @param l
     * @return
     */
    public List<String> checkPermission(List<String> l){
        List<String> list = new ArrayList<>();
        if (l.size() > 0){
            for (int j = 0; j < l.size(); j++){
                if (checkPermission(l.get(j))){
                    list.add(l.get(j));
                }
            }
        }
        return list;
    }

}
