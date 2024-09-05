import { listInterfaceInfoByPageUsingGet } from '@/services/api-backend/interfaceInfoController';
import { PageContainer } from '@ant-design/pro-components';
import { List, message } from 'antd';
import React, { useEffect, useState } from 'react';

/**
 * 主页
 * @returns 
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceInfo[]>([]);
  const [total, setTotal] = useState<number>(0);

  // 加载数据的逻辑
  const loadData = async (current = 1, pageSize = 5) => {
    setLoading(true);  // 表示正在加载
    try {
      const res = await listInterfaceInfoByPageUsingGet({
        current, pageSize
      });
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    } catch (error: any) {
      message.error('请求失败, ' + error.message);
    }
    setLoading(false);  // 表示加载完了
  };

  useEffect(() => {
    loadData();
  }, [])

  return (
    <PageContainer title="xiyan在线接口开放平台">
      <List
        className="my_list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}
        renderItem={(item) => {
          const apiLink = `/interface_info/${item.id}`;
          return (
            <List.Item
            actions={[<a key={item.id} href={apiLink}>查看</a>]}
          >
              <List.Item.Meta
                // avatar={<Avatar src={item.picture.large} />} // 头像
                title={<a href={apiLink}>{item.name}</a>}  // 改为接口文档的地址
                description={item.description}
              />
          </List.Item>
          );
        }}
      pagination={
        {
          showTotal(total: number){
            return '总数：' + total;
          },
          pageSize: 5,
          total,
          onChange(page, pageSize){
            loadData(page, pageSize);
          }
        }
      }
    />
    </PageContainer>
  );
};

export default Index;
