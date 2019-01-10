![logo](https://raw.githubusercontent.com/NikolaDai/NIRAS/master/logo.png)
## NIRAS：News Information Retrieval and Analysis System

Welcome！By the fast development of software and skills to plan, collect, and release news, the number of news content has been dramatically increased. How to obtain useful and suitable information from these big data means a lot not only for the readers but also for the authors who may rely heavily on historical news materials to generate comprehensive news.The core is WE NEED A SEARCH ENGINE。Despite the emergence of a number of commercial products in the industry, problems such as high fees and difficult customization have limited the further optimization of search services. The goal of the project is to build an open source, free and practical news information retrieval and analysis system (NIRAS) based on open source systems, which can meet the retrieval needs of news product production and explore a new way for news information retrieval.

## Getting started
1. JDK 1.8
2. Spring Boot 2.1.1 & gradle
3. Elasticsearch 6.5.3 (with HanLP plugin installed)
4. Intellij IDEA(recommended)

With the above software ready, run Elasticsearch 6.5.3 then BuildMappingAndImportData. Lastly, run NIRASApplication. Open your favourite browser, open our application by inputting "localhost:8080", then please enjoy it.

## Q&A and issue tracking

If you have any questions, feedback, or feature requests, don't hesitate to [add an issue][].

## Contributing

Welcome to join me! My wechat is 335860828.

## License

NIRAS is released under the MIT-licence.

## READing Materials
1. Spring-data-elasticsearch ：https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/ ; https://github.com/spring-projects/spring-data-elasticsearch
2. AIAS usage of spring-data-elasticsearch reference ：https://www.baeldung.com/spring-data-elasticsearch-tutorial
3. Pagination：https://www.logicbig.com/tutorials/spring-framework/spring-data/pagination-with-thymeleaf.html & https://www.baeldung.com/spring-thymeleaf-pagination & https://v4-alpha.getbootstrap.com/components/pagination/ & http://www.importnew.com/24722.html



[project wiki]: https://github.com/spring-io/sagan/wiki
[add an issue]: https://github.com/NikolaDai/NIRAS/issues

## NIRAS：新闻信息检索及分析系统
随着新闻策划、采集及发布等生产流程的优化，新闻内容数量呈现爆炸式增长。此外，新闻产品的制作对历史新闻资料的依赖程度很高，需要有准确、快速、全面的检索系统做支持。尽管行业内出现了一批商业化产品，但是其收费高、定制难等问题限制了检索服务的进一步优化。本文目标是，基于开源系统构建一套开源、免费且实用新闻信息检索及分析系统（NIRAS），能够满足新闻产品制作的检索需求，为新闻信息检索探索一条新路。
