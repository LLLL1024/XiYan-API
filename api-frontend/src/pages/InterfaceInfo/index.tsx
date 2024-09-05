import { getInterfaceInfoByIdUsingGet, invokeInterfaceInfoUsingPost } from '@/services/api-backend/interfaceInfoController';
import { PageContainer } from '@ant-design/pro-components';
import { useParams } from '@umijs/max';
import { Button, Card, Descriptions, Divider, Form, Input, message } from 'antd';
import React, { useEffect, useState } from 'react';

/**
 * 接口页面
 * @returns 
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  // 获取id
  const params = useParams();
  // 存储结果
  const [invokeRes, setInvokeRes] = useState<any>();
  const [invokeLoading, setInvokeLoading] = useState(false);


  // 加载数据的逻辑
  const loadData = async () => {
    if (!params.id){
      message.error("参数不存在");
      return;
    }
    setLoading(true);  // 表示正在加载
    try {
      const res = await getInterfaceInfoByIdUsingGet({
        id: Number(params.id)
      });
      setData(res.data);
    } catch (error: any) {
      message.error('请求失败, ' + error.message);
    }
    setLoading(false);  // 表示加载完了
  };

  useEffect(() => {
    loadData();
  }, [])

  const onFinish = async(values: any) => {
    if (!params.id){
      message.error("接口不存在");
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPost({
        id: params.id,
        ...values
      })
      setInvokeRes(res.data);
      message.success('请求成功');
    } catch (error: any) {
      message.error('请求失败, ' + error.message);
    }
    setInvokeLoading(false);
  };
  
  return (
    <PageContainer title="查看接口文档">
      <Card>
       {
         data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="接口状态">{ data.status ? "正常" : "关闭"}</Descriptions.Item>
            <Descriptions.Item label="描述">{ data.description }</Descriptions.Item>
            <Descriptions.Item label="请求地址">{ data.url }</Descriptions.Item>
            <Descriptions.Item label="请求方法">{ data.method }</Descriptions.Item>
            <Descriptions.Item label="请求参数">{ data.requestParams }</Descriptions.Item>
            <Descriptions.Item label="请求头">{ data.requestHeader }</Descriptions.Item>
            <Descriptions.Item label="响应头">{ data.responseHeader }</Descriptions.Item>
            <Descriptions.Item label="创建时间">{ data.createTime }</Descriptions.Item>
            <Descriptions.Item label="更新时间">{ data.updateTime }</Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在</>
        )
       }
      </Card>
      <Divider />
      <Card title="在线测试">
        <Form
          name="invoke"
          layout='vertical'
          onFinish={onFinish}
        >
          <Form.Item
            label="请求参数"
            name="userRequestParams" // 根据后端改的
          >
            <Input.TextArea />
          </Form.Item>
          <Form.Item wrapperCol={{ span: 16 }}>
            <Button type="primary" htmlType="submit">
              调用
            </Button>
          </Form.Item>
        </Form>
      </Card>
      <Divider />
      <Card title="测试结果" loading={invokeLoading}>
        {invokeRes}
      </Card>
    </PageContainer>
  );
};

export default Index;
