// 导入其它模块的成员，重命名之后再导出 - 相当于，把多个模块整合在一起
export {i as m,j} from './test03-export-02.js'

export let obj = {
    name:'zs',
    age:18
}