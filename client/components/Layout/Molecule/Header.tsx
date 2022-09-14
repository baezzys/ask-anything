import { css } from '@emotion/react';
import { useState } from 'react';
import pxToRem from 'utils/style/pxToRem';
import LinkToBack from '../atom/LinkToBack';
import LoginButton from '../atom/LoginButton';
import UserButton from '../atom/UserButton';

const Header = () => {
  const [isLogin] = useState(false);

  return (
    <div css={headerStyle}>
      <LinkToBack />
      {isLogin ? <UserButton /> : <LoginButton />}
    </div>
  );
};

const headerStyle = css`
  width: 100%;
  height: ${pxToRem(50)};
  box-sizing: border-box;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.05);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 ${pxToRem(10)};
`;

export default Header;
