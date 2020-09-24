$(function(){

    var data4Vue = {
        url:'homes',
        cate:[]
    }

    var vue = new Vue({
       el:'#home',
        data:data4Vue,
        mounted:function(){
           this.list();
        },
        methods:{
           list:function(){
               var url = this.url;
               axios.get(url).then(function(response){
                   vue.cate = response.data;
                   $("a.home2").css("color", "rgb(255, 50, 50)");
               })
           }
        }
    });
})