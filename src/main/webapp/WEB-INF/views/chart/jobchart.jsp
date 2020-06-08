<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript"
            src="/static/plugins/echart/echarts.js"></script>
    <script type="text/javascript">
        $(function () {

            var colors = ['#5793f3', '#d14a61', '#675bba'];

            var option = {
                color: colors,

                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross'
                    }
                },
                grid: {
                    right: '20%'
                },
                toolbox: {
                    feature: {
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                legend: {
                    data: ['培训前工资', '培训后工资','培训后平均薪资']
                },
                xAxis: [
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: ${type}
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        name: '培训前工资',
                        min: 0,
                        max: ${max},
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: colors[0]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    },
                    {
                        type: 'value',
                        name: '培训后工资',
                        min: 0,
                        max: ${max},
                        position: 'right',
                        offset: 80,
                        axisLine: {
                            lineStyle: {
                                color: colors[1]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    },
                    {
                        type: 'value',
                        name: '培训后平均薪资',
                        min: 0,
                        max: ${avgMax},
                        position: 'left',
                        axisLine: {
                            lineStyle: {
                                color: colors[2]
                            }
                        },
                        axisLabel: {
                            formatter: '{value} 元'
                        }
                    }
                ],
                series: [
                    {
                        name: '培训前工资',
                        type: 'bar',
                        data: ${before}
                    },
                    {
                        name: '培训后工资',
                        type: 'bar',
                        yAxisIndex: 1,
                        data: ${after}
                    },
                    {
                        name: '培训后平均薪资',
                        type: 'line',
                        yAxisIndex: 2,
                        data: ${avg}
                    }
                ]
            };
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        });
    </script>
</head>
<body>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</body>
</html>
