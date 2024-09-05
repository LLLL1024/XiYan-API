import {LogoutOutlined, SettingOutlined, UserOutlined} from '@ant-design/icons';
import {history, Link, useModel} from '@umijs/max';
import {Avatar, Button, Spin} from 'antd';
import {createStyles} from 'antd-style';
import type {MenuInfo} from 'rc-menu/lib/interface';
import React, {useCallback} from 'react';
import {flushSync} from 'react-dom';
import HeaderDropdown from '../HeaderDropdown';
import {userLogoutUsingPost} from "@/services/api-backend/userController";
import menu from 'antd/es/menu';

export type GlobalHeaderRightProps = {
  menu?: boolean;
  children?: React.ReactNode;
};

export const AvatarName = () => {
  const {initialState} = useModel('@@initialState');
  const {loginUser} = initialState || {};
  // return <span className="anticon">{loginUser?.userName}</span>;
  return (
    <span className="action">
       {loginUser?.userAvatar ? (
          <Avatar size="small" src={loginUser?.userAvatar} />
        ) : (
          <Avatar size="small" icon={<UserOutlined />} />
        )}
      <span className="anticon">{loginUser?.userName ?? '无名'}</span>
    </span>
  );
};

const useStyles = createStyles(({token}) => {
  return {
    action: {
      display: 'flex',
      height: '48px',
      marginLeft: 'auto',
      overflow: 'hidden',
      alignItems: 'center',
      padding: '0 8px',
      cursor: 'pointer',
      borderRadius: token.borderRadius,
      '&:hover': {
        backgroundColor: token.colorBgTextHover,
      },
    },
  };
});

export const AvatarDropdown: React.FC<GlobalHeaderRightProps> = ({children}) => {
  const {styles} = useStyles();

  const {initialState, setInitialState} = useModel('@@initialState');

  const onMenuClick = useCallback(
    (event: MenuInfo) => {
      const {key} = event;
      if (key === 'logout') {
        flushSync(() => {
          setInitialState((s) => ({...s, currentUser: undefined}));
        });
        userLogoutUsingPost();
        const {search, pathname} = window.location;
        const redirect = search + pathname;
        history.replace("/user/login", {redirect})
        return;
      }
      history.push(`/account/${key}`);
    },
    [setInitialState],
  );

  const { loginUser } = initialState || {};

  if (!loginUser) {
    return (
      <Link to="/user/login">
        <Button type="primary" shape="round">
          登录
        </Button>
      </Link>
    );
  }

  const menuItems = [
    ...(menu
      ? [
          {
            key: 'center',
            icon: <UserOutlined />,
            label: '个人中心',
          },
          {
            key: 'settings',
            icon: <SettingOutlined />,
            label: '个人设置',
          },
          {
            type: 'divider' as const,
          },
        ]
      : []),
    {
      key: 'logout',
      icon: <LogoutOutlined />,
      label: '退出登录',
    },
  ];

  return (
    <HeaderDropdown
      menu={{
        selectedKeys: [],
        onClick: onMenuClick,
        items: menuItems,
      }}
    >
      {children}
    </HeaderDropdown>
  );
};