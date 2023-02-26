import { css } from "@emotion/react";
import LoginModal from "components/Modal/LoginModal";
import { useState } from "react";
import colors from "utils/style/colors";
import pxToRem from "utils/style/pxToRem";

const LoginButton = () => {
  const [isOpen, setIsOpen] = useState(false);
  const open = () => {
    setIsOpen(true);
  };
  const close = () => {
    setIsOpen(false);
  };

  return (
    <>
      <button css={buttonStyle} onClick={open} type="button">
        로그인
      </button>

      <LoginModal isOpen={isOpen} close={close} />
    </>
  );
};

const buttonStyle = css`
  color: ${colors.pink[100]};
  padding: 0 ${pxToRem(10)};
`;

export default LoginButton;
