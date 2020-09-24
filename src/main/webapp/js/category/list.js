$(function(){
    //获取路径上的id
    var kid = getUrlParms("kid");
    //存储数据
    var data4Vue = {
        uri: 'categories',
        beans: [],
        bena:{id:0,name:'',kind:{id:0}},
        pagination:{},
        kind:'',
        user:{id:0,name:'',fileImage:{id:0}}
    }

    //创建Vue对象
    var vue = new Vue({
        el:'#category',
        data:data4Vue,
        mounted:function(){ //vue对象加载成功后调用
            //调用分页查询
            this.list(0);
            this.get(kid);
            this.getUser();
        },
        methods:{
            get:function(id){
              var url = "kinds/" + kid;
              axios.get(url).then(function(response){
                  vue.kind = response.data;
              })
            },
            list:function(stary){
                //访问的路径
                var url = "kinds/"+kid+"/"+this.uri+"?stary=" + stary;
                axios.get(url).then(function(response){
                    //获取返回的集合对象
                    vue.beans = response.data.content;
                    //获取分页对象
                    vue.pagination = response.data;
                });
            },

            add:function(){
                if(!checkEmpty(this.bena.name, "分类名称"))
                    retunr;
                //增加
                var url = this.uri;
                this.bena.kind.id = kid;
                axios.post(url, this.bena).then(function(response){
                    //调用查询
                    vue.list(0);
                    //把bena对象复位
                    vue.bena = {id:0,name:'',kind:{id:0}};
                });
            },

            deleteBena:function(id){
                //确认要删除
                if(!checkDeleteLink())
                    return;
                var url = this.uri + "/" + id;
                axios.delete(url).then(function(response){
                    //如果返回的不为空就打印
                    if(0 != response.data.length)
                        alert(response.data)
                    else
                    //调用查询
                        vue.list(0);
                });
            },
            getUser:function(){
                var url = "users";
                axios.get(url).then(function(response){
                    vue.user = response.data;
                })
            },
            deleteUser:function(){
                var url = "users";
                axios.delete(url).then(function(response){
                    location.href = "admin";
                })
            },

            jump:function(page){
                //跳转首页、尾页、上一页、下一页
                jump(page,vue);
            },
            jumpByNumber: function(start){
                //跳转中间的部分
                jumpByNumber(start,vue);
            },
        }
    });
})