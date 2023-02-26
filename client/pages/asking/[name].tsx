import AskingBody from "components/Asking/AskingBody";
import AskingHeader from "components/Asking/AskingHeader";
import Head from "next/head";
import { useRouter } from "next/router";
import { useState } from "react";
import { Asks, User } from "types/asking";

function Answered() {
  const [user, setUser] = useState<User>({
    id: 123,
    name: "강희",
    content:
      "안녕하세요~ 그림을 좋아하는 개발자입니다.안녕하세요~ 그림을 좋아하는 개발자입니다.안녕하세요~ 그림을 좋아하는 개발자입니다.안녕하세요~ 그림을 좋아하는 개발자입니다.안녕하세요~ 그림을 좋아하는 개발자입니다.안녕하세요~ 그림을 좋아하는 개발자입니다.",
    profile_url:
      "https://velog.velcdn.com/images/heelieben/profile/c8f86d98-ecf5-4598-a07d-7356a5f7b5e5/image.png",
  });

  const [asks, setAsks] = useState<Asks>({
    total_count: 2,
    asks: [
      {
        id: 0,
        question: {
          date: new Date(),
          contents: "요즘 즐겨듣는 노래는?",
        },
        answer: {
          name: "강희",
          date: new Date(),
          profile_url:
            "https://velog.velcdn.com/images/heelieben/profile/c8f86d98-ecf5-4598-a07d-7356a5f7b5e5/image.png",
          contents: "Kick Back!",
        },
      },
      {
        id: 2,
        question: {
          date: new Date(),
          contents: "어떤 유튜버를 좋아합니까!!",
        },
        answer: {
          name: "강희",
          date: new Date(),
          profile_url:
            "https://velog.velcdn.com/images/heelieben/profile/c8f86d98-ecf5-4598-a07d-7356a5f7b5e5/image.png",
          contents: "침착맨ㅋㅋ",
        },
      },
    ],
  });

  const {
    query: { name },
  } = useRouter();

  return (
    <div>
      <Head>
        <title>Ask Anything - {name}</title>
        <meta name="description" content="Ask anything" />
        <link rel="icon" href="/assets/images/gamza.png" />
      </Head>

      <main>
        <AskingHeader {...user} />
        <AskingBody {...asks} />
      </main>
    </div>
  );
}

export default Answered;
