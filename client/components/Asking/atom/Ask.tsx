import { css } from "@emotion/react";
import { PostType } from "types/asking";
import AskItem from "./AskItem";

function Ask({ question, answer }: { question: PostType; answer: PostType }) {
  return (
    <div css={ask}>
      <AskItem {...question} />
      {answer ? <AskItem isAnswer={true} {...answer} /> : <></>}
    </div>
  );
}
const ask = css``;

export default Ask;
