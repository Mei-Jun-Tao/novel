$(function(){

    var data4Vue = {
        uri:'users',
        user:{id:0,name:'',password:'',realname:'',cellNumber:'',emallAddress:'',address:'',postalCode:'',fileImage:{id:0}},
        worn:'',
        news:'',
        affirm:'',
        valu:'mess',
        file:'',
        bena:{id:0,type:'Head_Image'}
    }

    var vue = new Vue({
        el:'#update',
        data:data4Vue,
        mounted:function(){
            this.getUser();
            mes();
        },
        methods:{
            getUser:function(){
                var url = this.uri;
                axios.get(url).then(function(response){
                    vue.user = response.data;
                    vue.worn = vue.user.password;
                })
            },
            deleteUser:function(){
                var url = "users";
                axios.delete(url).then(function(response){
                    location.href = "admin";
                })
            },
            update:function(){
                if(this.valu == "mess") {
                    if (!checkEmpty(this.user.name, "作者名"))
                        return;
                    if (!checkEmpty(this.user.realname, "真实名"))
                        return;
                    if (!checkEmpty(this.user.cellNumber, "手机号码"))
                        return;
                    if (!checkEmpty(this.user.emallAddress, "邮箱地址"))
                        return;
                    if (!checkEmpty(this.user.address, "所在地址"))
                        return;
                    if (!checkEmpty(this.user.postalCode, "邮政编号"))
                        return;
                }
                if(this.valu == "pass") {
                    if (!checkEmpty(this.worn, "旧密码"))
                        return;
                    if (!checkEmpty(this.news, "新密码"))
                        return;
                    if (!checkEmpty(this.affirm, "确认密码"))
                        return;
                    if (!isVerify(this.worn, this.user.password, "请输入正确的旧密码"))
                        return;
                    if (!isVerify(this.news, this.affirm, "两次输入的密码不一致"))
                        return;
                    this.user.password = this.news;
                }
                var url = this.uri;
                axios.put(url, this.user).then(function(response){
                    vue.user = response.data;
                    if(vue.valu == "mess")
                        alert("保存信息成功");
                    if(vue.valu == "pass") {
                        alert("修改密码成功");
                        vue.news = null;
                        vue.affirm = null;
                        vue.worn = vue.user.password;
                    }
                })
            },
            updateImage:function(response){
                var url = "updateImages";
                var formData = new FormData();
                formData.append("type", "Head_Image");
                formData.append("image", this.file);
                axios.post(url, formData).then(function(response){
                    vue.getUser();
                    vue.bena = response.data;
                    alert("上传成功");
                    vue.file = null;
                    $("input.file").val("");
                })
            },
            emptys:function(){
                this.worn = null;
                this.news = null;
                this.affirm = null;
            },
            convert:function(e){

                //获取类属性的集合
                var clszz = e.target.classList;
                this.valu = clszz;
                if(clszz == "mess")
                    mes();
                if(clszz == "imgss")
                    $("div.message").css("height", "420px");
                if(clszz == "pass")
                    $("div.message").css("height", "300px");
                $("div.button button").each(function(){
                    $("div.button button").css("border", "none");
                    $("button." + clszz).css("border-bottom", "2px solid rgb(21, 180, 141)");
                });
                $("div.message>div").each(function(){
                    var c = $(this).attr("class");
                    if(clszz == c)
                        $("div." + c).fadeIn(2000);
                    else
                        $("div." + c).hide();
                });
            },
            getFile:function(event){
                this.file = event.target.files[0];
            }
        }
    })
})