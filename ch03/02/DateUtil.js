// 立即执行函数 - 自调用函数
;(function(){
    // 判断内置对象是否已经存在toDate方法
    if(!Date.prototype.toDate) {
        // 扩展toDate方法 - 逐个扩展
        Date.prototype.toDate = function( pattern ) {
            // pattern(略)
            // console.log(this)
            let year = this.getFullYear();

            let month = this.getMonth()+1 > 9 ? (this.getMonth() + 1) : '0'+ (this.getMonth() + 1) ;

            let date = this.getDate() > 9 ? this.getDate() : '0' + this.getDate() ;

            return year + '-' + month + '-' + date ;
        }
    }
})() ;