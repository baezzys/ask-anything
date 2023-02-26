export type User = {
  id: number;
  name: string;
  content?: string;
  profile_url?: string;
};

export type PostType = {
  name?: string;
  profile_url?: string;
  date: Date;
  contents: string;
};

export type Ask = {
  id: number;
  question: PostType;
  answer: PostType;
};

export type Asks = {
  asks: Ask[];
  total_count: number;
};
