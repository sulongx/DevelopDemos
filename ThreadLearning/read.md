# 线程练习流程理解文档


## 六、多阶段运行任务Phaser


### 1.例子：关键词抽取算法

> **TF-IDF算法：**  
> 关键词抽取算法的一种，可以用文档分类、检索  
> 公式:  
> &ensp;&ensp;TF-IDF =F<sub>t,d</sub> * Log( N/N<sub>t</sub> );  

_参数说明：_
 * **F<sub>t,d</sub>:** 单词t在文档d中出现的次数
 * **N:** 集合中文档的数量 
 * **N<sub>t</sub>:** 含单词t的文档的数量
 
  