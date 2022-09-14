import { css } from '@emotion/react';
import A11yHidden from 'components/common/A11yHidden';
import Modal from 'components/Modal/Modal';
import Image from 'next/image';
import colors from 'utils/style/colors';
import pxToRem from 'utils/style/pxToRem';
import { GoAlert } from 'react-icons/go';

type LoginModalProps = {
  type?: 'logo' | 'message';
  isOpen: boolean;
  close: () => void;
};

const LoginModal = ({ type = 'logo', isOpen, close }: LoginModalProps) => (
  <Modal isOpen={isOpen} close={close}>
    <Modal.Title>
      {type === 'logo' && (
        <>
          <A11yHidden>에스크 에니띵</A11yHidden>
          <div css={logoStyle}>
            <Image
              src="/assets/images/gamza.png"
              layout="fixed"
              width={52}
              height={50}
            />
            <Image
              src="/assets/images/ask-anything.png"
              layout="fixed"
              width={151}
              height={36}
            />
          </div>
        </>
      )}
      {type === 'message' && (
        <>
          <GoAlert size={20} />
          <span css={alertStyle}>로그인이 필요한 서비스입니다.</span>
        </>
      )}
    </Modal.Title>

    <Modal.Title>
      <h2 css={messageStyle}>소셜 계정으로 로그인 하기</h2>
    </Modal.Title>

    <Modal.Button>구글로 로그인하기</Modal.Button>
    <Modal.Button buttonType="kakao">카카오로 로그인하기</Modal.Button>
  </Modal>
);

const alertStyle = css`
  padding-left: ${pxToRem(10)};
`;

const messageStyle = css`
  color: ${colors.gray[400]};
  font-size: ${pxToRem(12)};
  margin-top: ${pxToRem(10)};
`;

const logoStyle = css`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export default LoginModal;
