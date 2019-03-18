import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) throws ParseException {
        String input="photo.jpg, Warsaw, 2013-09-05 14:08:15\n" +
                "john.png, London, 2015-06-20 15:13:22\n" +
                "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" +
                "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n" +
                "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" +
                "BOB.jpg, London, 2015-08-05 00:02:03\n" +
                "notredame.png, Paris, 2015-09-01 12:00:00\n" +
                "me.jpg, Warsaw, 2013-09-06 15:40:22\n" +
                "a.png, Warsaw, 2016-02-13 13:33:50\n" +
                "b.jpg, Warsaw, 2016-01-02 15:12:22\n" +
                "c.jpg, Warsaw, 2016-01-02 14:34:30\n" +
                "d.jpg, Warsaw, 2016-01-02 15:15:01\n" +
                "e.png, Warsaw, 2016-01-02 09:49:09\n" +
                "f.png, Warsaw, 2016-01-02 10:55:32\n" +
                "g.jpg, Warsaw, 2016-02-29 22:13:11";

        System.out.println("결과는\n"+test(input));
    }

    public static String test(String str) throws ParseException {
        String[] strArray = str.split("\\r?\\n");
        Map<String, String> map = new HashMap<>();
        Set<String> citySet = new HashSet<>();
        List<Image> imageList = new ArrayList<>();

        // Image
        for(int i=0; i<strArray.length; i++) {
            String[] imageStr = strArray[i].split(", ");
            Image image = new Image(imageStr[0], imageStr[1], new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(imageStr[2]) );
            citySet.add(imageStr[1]);
            imageList.add(image);
        }

        // Order
        for (String city : citySet) {
            List<Image> cityList = imageList.stream().filter(image -> image.getCity().equals(city)).collect(Collectors.toList());
            setOrder(map, cityList);
        }

        StringBuffer stringBuffer = new StringBuffer();

        for (Image image : imageList) {
            String order = map.get(image.getId());
            image.setImgName(image.getCity()+order+".jpg");
            stringBuffer.append(image.getImgName()+"\n");
        }
        return stringBuffer.toString();
    }

    public static void setOrder(Map<String, String> map, List<Image> cityList) {
        int i = 1;

        Collections.sort(cityList, new Comparator<Image>() {
            @Override
            public int compare(Image o1, Image o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });

        for(Image image : cityList) {
            map.put(image.getId(), String.valueOf(i++));
        }
    }
}

class Image {
    String id;
    String imgName;
    String city;
    Date date;

    public Image(String imageName, String city, Date date){
        this.id = UUID.randomUUID().toString();
        this.imgName=imageName;
        this.city=city;
        this.date=date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", imgName='" + imgName + '\'' +
                ", city='" + city + '\'' +
                ", date=" + date +
                '}';
    }
}
