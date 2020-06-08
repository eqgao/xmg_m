package test;

import com._520it.crm.domain.Checkon;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/25.
 */
public class MDTest {
    @Test
    public void testMd() {

        System.out.println(new Md5Hash("666", "root").toString());
    }
    @Test
    public void test() throws  Exception{
        Checkon checkon = new Checkon();



        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date parse = format.parse("09:00:00");
        Date signintime = checkon.getSignintime();
        Date date = new Date();
        if (parse.getTime() > signintime.getTime()){

            System.out.println("我大");

        }else {
            System.out.println("我小");

        }





    }

}
