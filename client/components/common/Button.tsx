import { css } from "@emotion/react";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  mode?: string;
}

function Button({ mode, ...res }: ButtonProps) {
  return <button css={button} {...res} />;
}

const button = css`
  padding: 10px 22px;
  border: 1px solid #000;
  border-radius: 5px;
`;

export default Button;
