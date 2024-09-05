import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import React from 'react';

const Footer: React.FC = () => {
  const defaultMessage = 'xiyan出品';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      style={{
        background: 'none',
      }}
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        {
          key: 'github',
          title: <><GithubOutlined /> LLLL1024 GitHub</>,
          href: 'https://gitee.com/lwjhappy',
          blankTarget: true,
        },
        {
          title: 'ICP备案号：赣ICP备2024039351号-1',
          href: "https://beian.miit.gov.cn/",
          blankTarget: true,
        },
      ]}
    />
  );
};

export default Footer;
