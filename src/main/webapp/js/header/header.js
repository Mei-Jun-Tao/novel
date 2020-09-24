//判断是否为空
function checkEmpty(value,text){
    if(null==value || value.length==0){
        alert(text+ "不能为空");
        return false;
    }
    return true;
}

//显示数据的不能为空
function userEmpty(value, name ,vue){
    if(null==value || value.length==0){
        vue.emptys = name;
        return false;
    }
    return true;
}

//两次的不一致
function isEmpty(id, name, val, vue){
    var value = $("input." + id).val();
    if(value != val){
        vue.emptys = name;
        return false;
    }
    return true;
}
function empty(vue, value){
    vue.isbool = value;
    $("span.glyphicon-exclamation-sign").hide();
    $("span#" + vue.isbool).show();
}
//判断两次密码是否一致
function isVerify(name, value, text){
    if(name != value){
        alert(text);
        return false;
    }
    return true;
}

//设置高度
function mes(){
    var number=0;
    $("div.mess div").each(function(){
        number ++;
    });
    var height = $("div.mess div").innerHeight();
    var value;
    if(number%2 == 0){
        value = (height + 20) * number/2;
    }else{
        value = (height + 20) * (number/2 + 1);
    }
    $("div.message").css("height", value + "px");
}

//获取地址栏参数的函数
function getUrlParms(para){
    var search=location.search; //页面URL的查询部分字符串
    var arrPara=new Array(); //参数数组。数组单项为包含参数名和参数值的字符串，如“para=value”
    var arrVal=new Array(); //参数值数组。用于存储查找到的参数值

    if(search!=""){
        var index=0;
        search=search.substr(1); //去除开头的“?”
        arrPara=search.split("&");

        for(i in arrPara){
            var paraPre=para+"="; //参数前缀。即参数名+“=”，如“para=”
            if(arrPara[i].indexOf(paraPre)==0&& paraPre.length<arrPara[i].length){
                arrVal[index]=decodeURI(arrPara[i].substr(paraPre.length)); //顺带URI解码避免出现乱码
                index++;
            }
        }
    }

    if(arrVal.length==1){
        return arrVal[0];
    }else if(arrVal.length==0){
        return null;
    }else{
        return arrVal;
    }
}

//判断是否数字 (小数和整数)
function checkNumber(value, text){

    if(value.length==0){
        alert(text+ "不能为空");
        return false;
    }
    if(isNaN(value)){
        alert(text+ "必须是数字");
        return false;
    }
    return true;
}
//判断是否整数
function checkInt(value, text){

    if(value.length==0){
        alert(text+ "不能为空");
        return false;
    }
    if(parseInt(value)!=value){
        alert(text+ "必须是整数");
        return false;
    }
    return true;
}

//确实是否要删除
function checkDeleteLink(){
    var confirmDelete = confirm("确认要删除");
    if(confirmDelete)
        return true;
    return false;
}
//跳转函数
function jump(page,vue){
    if('first'== page && !vue.pagination.first)
        vue.list(0);

    else if('pre'== page &&	vue.pagination.hasPrevious )
        vue.list(vue.pagination.number-1);

    else if('next'== page && vue.pagination.hasNext)
        vue.list(vue.pagination.number+1);

    else if('last'== page && !vue.pagination.last)
        vue.list(vue.pagination.totalPages-1);
}
//跳转函数
function jumpByNumber(start,vue){
    if(start!=vue.pagination.number)
        vue.list(start);
}
//